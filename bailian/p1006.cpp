#include <iostream>

#define PHY_PERIOD 23
#define EMO_PERIOD 28
#define INT_PERIOD 33

using namespace std;

int res_on_today(int peak, int period, int today) {
  peak %= period;

  int res;
  if (peak < today)
    res = (today - peak) % period;
  else
    res = (period - peak + today) % period;

  return res;

}

int main() {
  int p, e, i, d;

  int cnt = 1;
  while (cin >> p >> e >> i >> d) {
    if (p == -1 && e == -1 && i == -1 && d == -1)
      break;
    
    i = res_on_today(i, INT_PERIOD, d);
    int acc = d + (INT_PERIOD - i);

    while ((acc - p) % PHY_PERIOD != 0 || (acc - e) % EMO_PERIOD != 0)
      acc += INT_PERIOD;

    cout << "Case " << cnt << ": the next triple peak occurs in "
      << (acc - d) << " days." << endl;

    cnt++;
  }
}
