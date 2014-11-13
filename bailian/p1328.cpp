#include <iostream>
#include <algorithm>
#include <cmath>

using namespace std;

typedef struct Point {
  int x;
  int y;
} Point;

int solve(Point a[], int n, int d);
int shift(Point a[], int n, int d, int idx);
bool cmpX(Point a, Point b);
bool cmpY(Point a, Point b);
bool cmp(Point a, Point b);

int main() {
  int n, d;

  int cnt = 0;
  while (cin >> n >> d) {
    if (n == 0 && d == 0)
      break;

    cnt++;
    Point a[n];

    for (int i = 0; i < n; i++)
      cin >> a[i].x >> a[i].y;
    sort(a, a + n, cmp);

    int res = solve(a, n, d);

    cout << "Case " << cnt << ": " << res << endl;
  }
}

int solve(Point a[], int n, int d) {
  int cnt = 0;

  int idx = 0;
  while (idx < n) {
    int tmp = shift(a, n, d, idx);

    if (tmp == -1)
      return -1;

    cnt++;
    idx += tmp;
  }

  return cnt;
}

int shift(Point a[], int n, int d, int idx) {
  int ret = 1;

  if (a[idx].y > d)
    return -1;

  int radarPos = sqrt(d^2 - a[idx].y^2) + a[idx].x;

  for (int i = idx + 1; i < n; i++) {
    if (i == n)
      break;

    int dist = d^2 - (a[i].x - radarPos)^2 - a[i].y^2;

    if (dist >= 0)
      ret++;
    else
      break;
  }

  return ret;
}

bool cmp(Point a, Point b) {
  if (a.x != b.x)
    return a.x < b.x; 
  else
    return a.y < b.y;
}

bool cmpX(Point a, Point b) {
  return a.x < b.x;
}

bool cmpY(Point a, Point b) {
  return a.y < b.y;
}
