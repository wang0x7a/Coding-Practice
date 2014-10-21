#include <iostream>
#include <cmath>
#include <algorithm>

using namespace std;

#define MAX_PAIR_NUM 1000

typedef struct record {
  int pos;
  int pixel;
} record;

int pixel_pair[MAX_PAIR_NUM][2];
int img_width;

int cmp(const record *a, const record *b);

int main() {
  while (cin >> img_width && img_width > 0) {
    int num, pixel;
    int pair_cnt  = 0;
    int pixel_cnt = 0;

    while (cin >> pixel >> num && num != 0 && pixel != 0) {
      pixel_pair[pair_cnt][0] = pixel;

      pixel_cnt += num;
      pixel_pair[pair_cnt][1] = pixel_cnt;

      pair_cnt++;
    }

    for (int k = 0; k < pair_cnt; k++) {
      int center = pixel_pair[k][1];
      int row    = (center - 1) / img_width;
      int col    = (cneter - 1) % img_width;
      
      for (int i = row - 1; i <= row + 1; i++)
        for (int j = col - 1; j <= col + 1; j++) {
          int pos = i * img_width + j + 1;
          if (i < 0 || pos > pixel_cnt || j < 0 || j == img_width)
            continue;
        }
    }

    /*
    for (int i = 0; i < pair_cnt; i++)
      cout << pixel_pair[i][0] << " " << pixel_pair[i][1] << endl;

    cout << pixel_cnt << endl;
    */
  }
}
