#include <iostream>
#include <algorithm>

#define MAX_DEV_NUM 10000

using namespace std;

class Device {
  public:
    int id;
    int bandwidth;
    int price;
};

bool cmp(const Device& a, const Device& b);

int main() {
  int t;
  
  cin >> t;

  for (int i = 0; i < t; i++) {
    int n;
    cin >> n;

    Device dev[MAX_DEV_NUM];
    int idx = 0;
    for (int j = 1; j <= n; j++) {
      int m;
      cin >> m;

      int b, p;
      for (int cnt = 0; cnt < m; cnt++) {
        cin >> b >> p;

        dev[idx].id        = j;
        dev[idx].price     = p;
        dev[idx].bandwidth = b;

        idx++;
      }
    }

    sort(dev, dev + idx, cmp);

    /*
    for (int i = 0; i < idx; i++)
      cout << dev[i].id << " " << dev[i].bandwidth  << " "
        << dev[i].price << endl;
    */
  }
}

bool cmp(const Device& a, const Device& b) {
  if (a.bandwidth != b.bandwidth)
    return a.bandwidth < b.bandwidth;
  else if (a.price != b.price)
    return a.price < b.price;
  else
    return a.id < b.id;
}
