#include <iostream>

using namespace std;

void solve(int l, int m);

int main()
{
  int n;
  
  cin >> n;

  int l, m;
  for (int i = 0; i < n; i++)
  {
    cin >> l >> m;

    solve(l, m);
  }
}

void solve(int l, int m)
{
  int tmp;

  int earliest = 0;
  int latest   = 0;
  for (int i = 0; i < m; i++)
  {
    cin >> tmp;

    int rest = l - tmp;

    if (tmp > rest)
    {
      if (tmp > latest)
        latest = tmp;

      if (rest > earliest)
        earliest = rest;
    }
    else
    {
      if (tmp > earliest)
        earliest = tmp;
      if (rest > latest)
        latest = rest;
    }

  }
  
  cout << earliest << " " << latest << endl;
}
