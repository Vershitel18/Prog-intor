#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int main() {
    long long n, m, p;
    cin >> n >> m >> p;
    vector<long long> med(n);
    for (long long i = 0; i < n; i++) {
        cin >> med[i];
    }
    sort(med.begin(),med.end());
    int i = n-1;
    int med_in_back = 0;
    long long count = 0;
    while (m > 0 && i >= 0) {
        long long buck = med[i]/p;
        if (m > buck) {
            med_in_back = buck * p;
            m -= buck;
        } else {
            med_in_back = p * m;
            m = 0;
        }
        count += med_in_back;
        med[i] %= p;
        i -= 1;
    }

    if (m > 0) {
        sort(med.begin(),med.end());
        i = n-1;
        while (m > 0 && i >= 0 && med[i] != 0) {
            count += med[i];
            m -= 1;
            i -= 1;
       } 
    }
    cout << count;
}