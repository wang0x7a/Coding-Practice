#include <iostream>
#include <algorithm>

#define MAX_NUM 100 

using namespace std;

int main()
{
  int n;
  
  cin >> n;

  int a[MAX_NUM] = {0};

  int tmp;
  int i = 0;
  while (cin >> tmp && i < n)
  {
    a[i] = tmp;
    i++;

    if (i == n)
      break;
  }

  if (i < n)
    return -1;

  sort(a, a + n);

  int max = 0;
  for (int i = 2; i < n; i++)
  {
    int tmp = a[i - 2] + a[i - 1];
    if ((tmp > a[i]) && (tmp + a[i] > max))
    {
      max = tmp + a[i];
    }
  }

  cout << max << endl;

  /*
  for (int i = 0; i < n; i++)
    cout << a[i] << " ";

  cout << endl;
  */
}
