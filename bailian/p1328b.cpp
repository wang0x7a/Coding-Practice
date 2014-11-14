#include <iostream>
#include <cmath>
#include <algorithm>

using namespace std;

typedef struct Point {
  float x;
  float y;
} Point;

typedef struct Interval {
  float left;
  float right;
} Interval;

bool cmp(Point a, Point b);
int solve(Point p[], int n, float d);

int main() {
  int n;
  float d;

  int caseNum = 0;
  while (cin >> n >> d) {
    if (n == 0 && d == 0.0)
      break;

    caseNum++;

    Point p[n];
    for (int i = 0; i < n; i++)
      cin >> p[i].x >> p[i].y;

    sort(p, p + n, cmp);

    int radarNum = solve(p, n, d);

    cout << "Case " << caseNum << ": " << radarNum << endl;

    /*
    for (int i = 0; i < n; i++)
      cout << p[i].x << " " << p[i].y << endl;
    */
  }
}

bool cmp(Point a, Point b) {
  if (a.x != b.x)
    return a.x < b.x;
  else
    return a.y < b.y;
} 

int solve(Point p[], int n, float d) {
  int radarNum = 1;

  Interval intvl[n];

  for (int i = 0; i < n; i++) {
    if (p[i].y > d)
      return -1;

    float vertDist = sqrt(d * d - p[i].y * p[i].y);
    intvl[i].left  = p[i].x - vertDist;
    intvl[i].right = p[i].x + vertDist;
  }

  Interval merged = intvl[0];
  Interval tmp;
  for (int i = 1; i < n; i++) {
    tmp.left  = max(merged.left, intvl[i].left);
    tmp.right = min(merged.right, intvl[i].right);

    if (tmp.left > tmp.right) {
      radarNum++;
      merged = intvl[i];
    }
    else
      merged = tmp;
  }

  return radarNum;
}
