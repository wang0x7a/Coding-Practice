#include <iostream>
#include <algorithm>
#include <cmath>

using namespace std;

typedef struct Point {
  float x;
  float y;
} Point;

int solve(Point a[], int n, float d);
int shift(Point a[], int n, float d, int idx);
bool cmpX(Point a, Point b);
bool cmpY(Point a, Point b);
bool cmp(Point a, Point b);

int main() {
  int n;
  float d;

  int cnt = 0;
  while (cin >> n >> d) {
    if (n == 0 && d == 0.0)
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

int solve(Point a[], int n, float d) {
  int cnt = 0;

  int idx = 0;
  while (idx < n) {
    if (idx < n - 1 && a[idx].x == a[idx + 1].x) {
      idx++;
      continue;
    }

    int tmp = shift(a, n, d, idx);

    if (tmp == -1)
      return -1;

    cnt++;
    idx += tmp;
  }

  return cnt;
}

int shift(Point a[], int n, float d, int idx) {
  int ret = 1;

  if (a[idx].y > d)
    return -1;

  float radarPos = sqrt(d * d - a[idx].y * a[idx].y) + a[idx].x;

  for (int i = idx + 1; i < n; i++) {
    if (i == n)
      break;

    if (a[i].x == a[i - 1].x)
      continue;

    float diffX = a[i].x - radarPos;
    float dist = d * d - diffX * diffX - a[i].y * a[i].y;

    if (dist < 0)
      break;

    ret++;
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
