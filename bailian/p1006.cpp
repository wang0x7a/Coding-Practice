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
      //break;
      return 0;
    
    //int res_p = res_on_today(p, PHY_PERIOD, d);
    //int res_e = res_on_today(e, EMO_PERIOD, d);
    int res_i = res_on_today(i, INT_PERIOD, d);
    int new_day = d + (INT_PERIOD - res_i);
    int res_p = res_on_today(p, PHY_PERIOD, new_day);
    int res_e = res_on_today(e, EMO_PERIOD, new_day);
    //p = res_on_today(p, PHY_PERIOD, new_day);
    //e = res_on_today(e, EMO_PERIOD, new_day);
    //i = res_on_today(i, INT_PERIOD, new_day);

    int acc = new_day;
    //while (acc % EMO_PERIOD != (EMO_PERIOD - e) 
    //    || acc % PHY_PERIOD != (PHY_PERIOD - p))
    //while ((acc - d) % INT_PERIOD != 0 || (acc - d) % EMO_PERIOD != 0)
    //while (res_on_today(p, PHY_PERIOD, acc) != res_p 
    //    || res_on_today(e, EMO_PERIOD, acc) != res_e)
    while ((acc - p) % PHY_PERIOD != 0 || (acc - e) % EMO_PERIOD != 0)
      acc += INT_PERIOD;

    cout << "Case " << cnt << ": the next triple peak occurs in "
      << (acc - d) << " days." << endl;

    cnt++;
  }
}
