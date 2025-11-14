#include <iostream>
#include <iomanip>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    int n;
    double A;
    cin >> n >> A;
    double lo = 0.0, hi = A;
    double bestB = A;

    for (int i = 0; i < 1000; i++) {
        double h2 = (lo + hi) / 2.0;
        vector<double> higth(n); 
        higth[0] = A;
        higth[1] = h2;
        bool ok = true;
        double min_h = min(higth[1], higth[2]);
        for (int j = 2; j < n; j++) {
            higth[j] = 2.0 * higth[j-1] - higth[j-2] + 2.0;
            if (higth[j] < 0) ok = false;
            min_h = min(min_h, higth[j]);
        }
        if (ok && min_h >= 0) {
            bestB = higth[n-1];
            hi = h2;
        } else {
            lo = h2;
        }
    }

    cout << fixed << setprecision(2) << bestB << endl;
    return 0;
}