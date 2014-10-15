#include <iostream>
#include <cmath>

#define MAX_PAIR_NUM 1000

using namespace std;

int acc_num[MAX_PAIR_NUM + 1];
int pixels[MAX_PAIR_NUM + 1];

int above_idx = 0;
int center_idx = 1;
int below_idx;

int width;
int total_pixel_cnt;

int init(void);
int get_pixel_around(int idx);
int get_pixel(int row_idx, int idx, int center);
void update_row_idx(int idx);

int main() {
  while (cin >> width) {
    if (width == 0)
      break;

    int cnt = init();

    total_pixel_cnt = acc_num[cnt - 1];

    int tmp = width + 1;
    for (int i = 1; i < cnt; i++) {
      if (acc_num[i] >= tmp) {
        below_idx = i;
        break;
      }
    }

    int curr_pixel = get_pixel_around(1);
    int cnt_pixel = 1;
    int tmp_pixel;
    for (int idx = 2; idx <= total_pixel_cnt; idx++) {
      update_row_idx(idx);

      tmp_pixel = get_pixel_around(idx);
      if (tmp_pixel == curr_pixel)
        cnt_pixel++;
      else {
        cout << curr_pixel << " " << cnt_pixel << endl;
        cnt_pixel = 1;
      }
      curr_pixel = tmp_pixel;
    }

    cout << curr_pixel << " " << cnt_pixel;
    cout << "0 0" << endl;
    above_idx = 0;
    center_idx = 1;

    /*
    for (int i = 0; i < cnt; i++) {
      cout << pixels[i] << " " << acc_num[i] << endl;
    }
    cout << above_idx << endl;
    cout << center_idx << endl;
    cout << below_idx << endl;
    */
  }

  cout << "0" << endl;
}

int init() {
  acc_num[0] = 0;
  pixels[0] = -1;

  int cnt = 1;
  int prev = 0;

  int pixel, num;

  while (true) {
    cin >> pixel >> num;

    if (pixel == 0 && num ==0)
      break;

    pixels[cnt] = pixel;
    acc_num[cnt] = prev + num;

    prev = acc_num[cnt];
    cnt++;
  }

  return cnt;
}

int get_pixel_around(int idx) {
  int around[9];

  int above = idx - width;
  int below = idx + width;

  int max = 0;
  int tmp;

  for (int i = 0; i < 9; i++) {
    if (i >= 0 && i <= 2)
      around[i] = get_pixel(above_idx, above - 1 + i, above);
    else if (i >= 3 && i <= 5)
      around[i] = get_pixel(center_idx, idx - 4 + i, idx);
    else
      around[i] = get_pixel(below_idx, below - 7 + i, below);
  }


  /*
  cout << above_idx << " " << center_idx << " " << below_idx << " " << endl;
  cout << "====" << endl;
  for (int i = 0; i < 3; i++)
    cout << around[i] << " ";
  cout << endl;
  for (int i = 3; i < 6; i++)
    cout << around[i] << " ";
  cout << endl;
  for (int i = 6; i < 9; i++)
    cout << around[i] << " ";
  cout << endl;
  cout << "====" << endl;
  */

  for (int i = 0; i < 9; i++) {
    if (around[i] == -1)
      around[i] = around[4];

    tmp = abs(around[i] - around[4]);
    if (tmp > max)
      max = tmp;
  }

  return max;
}

int get_pixel(int row_idx, int idx, int center) {
  // when the current pixel is beyond the up or low boundary of the img
  if (center <= 0 || center > total_pixel_cnt)
    return -1;

  // when the current pixel is beyond the left or right boundary 
  /*
  if (center % width == 1 || center % width == 0)
    return -1;
  */

  if (idx < center && center % width == 1)
    return -1;

  if (idx > center && center % width == 0)
    return -1;

  if (idx  == acc_num[row_idx - 1])
    return pixels[row_idx - 1];
  else if (idx > acc_num[row_idx])
    return pixels[row_idx + 1];
  else
    return pixels[row_idx];
}

void update_row_idx(int idx) {
  int above = idx - width;
  int below = idx + width;

  if (above > 0 && above > acc_num[above_idx])
    above_idx++;

  if (idx > acc_num[center_idx])
    center_idx++;

  if (below <= total_pixel_cnt && below > acc_num[below_idx])
    below_idx++;
}
