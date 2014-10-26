#include <iostream>
#include <algorithm>
#include <cstring>

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
    int numOfOpts = 0;
    int numOfDevs = 0;
    for (int j = 1; j <= n; j++) {
      cin >> numOfDevs;

      int b, p;
      for (int cnt = 0; cnt < numOfDevs; cnt++) {
        cin >> b >> p;

        dev[numOfOpts].id        = j;
        dev[numOfOpts].price     = p;
        dev[numOfOpts].bandwidth = b;

        numOfOpts++;
      }
    }

    sort(dev, dev + numOfOpts, cmp);

    bool isVisited[numOfDevs];
    memset(isVisited, false, sizeof(isVisited));

    for (int i = 0; i < numOfOpts; i++)
      cout << dev[i].bandwidth << "\t" << dev[i].price << "\t" 
        << dev[i].id << endl;
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
