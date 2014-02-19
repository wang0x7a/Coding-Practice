#include <stdio.h> 

#define MAX_INT_NUM 100000
#define MAX_OP_NUM  200000

#define MAKE_MASK(i) 1 << i

//void init(unsigned char nums[], int n);
void init(unsigned short int nums[], int n);
void add(unsigned short int nums[], int n, int inc);
int query(unsigned short int nums[], int n, int bit);

int main() {
  int n, m;
  scanf("%d %d", &n, &m);

  //printf("%d, %d\n", n, m);

  unsigned short int nums[n];
  init(nums, n);

  int i = 0;
  char op;
  int val;
  while (i < m) {
    if (2 == scanf("%c %d", &op, &val)) {
      i++;

      if (op == 'C')
        add(nums, n, val);
      else if (op == 'Q')
        //printf("%d\n", MAKE_MASK(val));
        printf("%d\n", query(nums, n, val));
    }
  }
  /*
  for (i = 0; i < n; i++)
    printf("%d ", nums[i]);
  printf("\n");
  */
}

void init(unsigned short int nums[], int n) {
  int i;

  for (i = 0; i < n; i++)
    /* WHY: we cannot use %c here?? */
    //scanf("%c", &nums[i]);
    scanf("%d", &nums[i]);

  return;
}

void add(unsigned short int nums[], int n, int inc) {
  int i;
  for (i = 0; i < n; i++)
    nums[i] += inc;

  return;
}

int query(unsigned short int nums[], int n, int bit) {
  int mask = MAKE_MASK(bit);
  int res = 0;

  int i;
  for (i = 0; i < n; i++) {
    if (nums[i] & mask) res++;
  }
  return res;
}
