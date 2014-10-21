#include <iostream>
#include <cmath>
#include <algorithm>

using namespace std;

#define MAX_PAIR_NUM 1000

int pixel_pair[MAX_PAIR_NUM][2];
int img_width;

int main() {
  while (cin >> img_width && img_width > 0) {
    int num, pixel;
    int pair_cnt  = 0;
    int pixel_cnt = 0; 

    while (cin >> pixel >> num && num != 0 && pixel != 0) {
      pixel_pair[pair_cnt][0] = pixel; 
      pixel_pair[pair_cnt][1] = num;

      pair_cnt++;

      pixel_cnt += num;
    }

    for (int i = 0; i < pair_cnt; i++)
      cout << pixel_pair[i][0] << " " << pixel_pair[i][1] << endl;

    cout << pixel_cnt << endl;
  }
}
