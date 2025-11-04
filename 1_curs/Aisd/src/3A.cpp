#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

bool bin_searching(const vector<int>& A, int value) {
    int l = 0;
    int r = A.size();

    while (l < r) {
        int mid = (l + r) / 2;
        if (A[mid] == value) {
            return true;
        }
        if (A[mid] < value) {
            l = mid + 1;
        } else {
            r = mid;
        }
    }
    return false;
}

int main() {
    int n, k;
    cin >> n >> k;

    vector<int> N(n);
    for (int i = 0; i < n; i++) {
        cin >> N[i];
    }
    sort(N.begin(), N.end());

    vector<int> K(k);
    for (int i = 0; i < k; i++) {
        cin >> K[i];
    }

    for (int i = 0; i < k; i++) {
        if (bin_searching(N, K[i])) {
            cout << "YES" << endl;
        } else {
            cout << "NO" << endl;
        }
    }
    cout << endl;
    return 0;
}