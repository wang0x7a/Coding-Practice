#include <iostream>
#include <cmath>

#define MAX_PAIR_NUM 1000

using namespace std;

int acc_num[MAX_PAIR_NUM];
int pixels[MAX_PAIR_NUM];

int above_idx = 0;
int center_idx = 1;
int below_idx;

int width;
int total_pixel_cnt;

int init(void);
int get_pixel_around(int idxt);
int get_pixel(int row_idx, int idx, int center);

int main() {
  while (cin >> width) {
    if (width == 0)
      break;

    int cnt = init();

    total_pixel_cnt = acc_num[cnt - 1];

    for (int i = 0; i < cnt; i++)
      cout << acc_num[i] << " " << pixels[i] << endl;
  }
}

int init() {
  int cnt = 0;
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
}
