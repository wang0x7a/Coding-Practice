#include <iostream>
#include <algorithm>

using namespace std;

class Dna {
  public:
    string dna;
    int len;
    int inverse_pairs_num;

    Dna() {}

    Dna(string d) {
      dna = d;
      dna_copy = string(d);

      inverse_pairs_num = get_inverse_pairs();
    }

  private:
    string dna_copy;

    int get_inverse_pairs() {
      char tmp[dna.size()];

      return get_inverse_pairs(tmp, 0, dna.size() - 1);
    }

    int get_inverse_pairs(char tmp[], int s, int e) {
      int res = 0;

      if (s < e) {
        int c = (s + e) / 2;
        int left   = get_inverse_pairs(tmp, s, c);
        int right  = get_inverse_pairs(tmp, c + 1 , e);
        int across = count_inverse_pairs(tmp, s, c, e); 

        res = left + right + across;
      }

      return res;
    }

    int count_inverse_pairs(char tmp[], int left_start, int left_end, 
        int right_end) {
      int res = 0;

      int left_pos  = left_start;
      int right_pos = left_end + 1;
      int pos       = left_start; 

      while (left_pos <= left_end && right_pos <= right_end) {
        if (dna_copy[left_pos] <= dna_copy[right_pos])
          tmp[pos++] = dna_copy[left_pos++];
        else {
          res += (left_end - left_pos + 1);
          tmp[pos++] = dna_copy[right_pos++];
        }
      }

      while (left_pos <= left_end)
        tmp[pos++] = dna_copy[left_pos++];

      while (right_pos <= right_end)
        tmp[pos++] = dna_copy[right_pos++];

      for (pos--; pos >= left_start; pos--)
        dna_copy[pos] = tmp[pos];

      return res;
    }
};

bool cmp(Dna a, Dna b) {
  return a.inverse_pairs_num < b.inverse_pairs_num;
}

int main() {
  /*
  Dna dna1 = Dna("CCCGGGGGGA");
  Dna dna2 = Dna("TTTGGCCAAA");

  cout << dna1.inverse_pairs_num << " " << dna2.inverse_pairs_num << endl;
  */
  int len, num;

  cin >> len >> num;

  Dna a[num];

  string tmp;
  for (int i = 0; i < num; i++) {
    cin >> tmp;

    a[i] = Dna(tmp);
  }

  sort(a, a + num, cmp);

  for (int i = 0; i < num; i++)
    cout << a[i].dna << endl;

}
