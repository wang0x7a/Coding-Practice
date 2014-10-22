#include <iostream>
#include <cmath>
#include <algorithm>

using namespace std;

#define MAX_PAIR_NUM 1000

typedef struct Record {
  int pos;
  int pixel;
} Record;

int pixel_pair[MAX_PAIR_NUM][2];
int img_width;

Record result[MAX_PAIR_NUM * 9];

bool cmp(const Record& a, const Record& b);
int get_pixel_via_pos(int pos, int pair_cnt);
int encode_pixel(int pos, int pixel_cnt, int pair_cnt);

int main() {
  while (cin >> img_width && img_width > 0) {
    int num, pixel;
    int pair_cnt  = 0;
    int pixel_cnt = 0;

    int start_pos = 1;
    while (cin >> pixel >> num && num != 0 && pixel != 0) {
      pixel_pair[pair_cnt][0] = pixel;
      pixel_pair[pair_cnt][1] = start_pos;

      pixel_cnt += num;
      start_pos += num;
      pair_cnt++;
    }

    int idx = 0;
    for (int k = 0; k < pair_cnt; k++) {
      int center = pixel_pair[k][1];
      int row    = (center - 1) / img_width;
      int col    = (center - 1) % img_width;
      
      for (int i = row - 1; i <= row + 1; i++)
        for (int j = col - 1; j <= col + 1; j++) {
          int pos = i * img_width + j + 1;
          if (i < 0 || pos > pixel_cnt || j < 0 || j == img_width)
            continue;

          result[idx].pos   = pos;
          result[idx].pixel = encode_pixel(pos, pixel_cnt, pair_cnt); 
          
          idx++;
        }
    }

    sort(result, result + idx, cmp);

    /*
    for (int i = 0; i < idx; i++)
      cout << result[i].pixel << " " << result[i].pos << endl;
    */

    Record prev = result[0];
    if (idx > 1) {
      for (int i = 1; i < idx; i++) {
        if (prev.pixel != result[i].pixel) {
          cout << prev.pixel << " " << result[i - 1].pos - prev.pos << endl;
          prev = result[i];
        }
      }

      cout << result[idx - 1].pixel << " " 
        << result[idx - 1].pos - prev.pos << endl;
    }
    else
      cout << prev.pixel << " " << prev.pos << endl;

    cout << "0 0" << endl;

    /*
    for (int i = 0; i < pair_cnt; i++)
      cout << pixel_pair[i][0] << " " << pixel_pair[i][1] << endl;

    cout << pixel_cnt << endl;
    */
  }

  cout << 0 << endl;
}

bool cmp(const Record& a, const Record& b) {
  return a.pos < b.pos;
}

int get_pixel_via_pos(int pos, int pair_cnt) {
  if (pair_cnt == 1)
    return pixel_pair[0][0];

  int prev = pixel_pair[0][1];
  for (int i = 1; i < pair_cnt; i++) {
    if (pos >= prev && pos < pixel_pair[i][1])
      return pixel_pair[i - 1][0];
  }

  return pixel_pair[pair_cnt - 1][0];
}

int encode_pixel(int pos, int pixel_cnt, int pair_cnt) {
  int row = (pos - 1) / img_width;
  int col = (pos - 1) % img_width;

  int ret = 0;
  int tmp = 0;
  for (int i = row - 1; i <= row + 1; i++)
    for (int j = col - 1; j <= col + 1; j++) {
      int curr = i * img_width + j + 1;

      if (i < 0 || curr > pixel_cnt || j == img_width || j < 0 
          || curr == pos)
        continue;

      tmp = abs(get_pixel_via_pos(pos, pair_cnt) 
          - get_pixel_via_pos(curr, pair_cnt));

      if (tmp > ret)
        ret = tmp;
    }

  return ret;
}
