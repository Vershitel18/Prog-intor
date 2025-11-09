#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int bin_searching_left(const vector<int>& A, int value) {
    int l = 0;
    int r = A.size();
    int ans = -1;
    while (l < r) {
        int mid = (l + r) / 2;
        if (A[mid] == value) {
            ans = mid;
        }
        if (A[mid] < value) {
            l = mid + 1;
        } else {
            r = mid;
        }
    }
    return ans;
}

int bin_searching_right(const vector<int>& A, int value) {
    int l = 0;
    int r = A.size();
    int ans  = -1;
    while (l < r) {
        int mid = (l + r) / 2;
        if (A[mid] == value) {
            ans = mid;
        }
        if (A[mid] <= value) {
            l = mid + 1;
        } else {
            r = mid;
        }
    }
    return ans;
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
        if (bin_searching_right(N, K[i]) == -1) {
            cout << 0 << endl;
        } else {
            cout << bin_searching_left(N, K[i])+1 << " " << bin_searching_right(N, K[i])+1 << endl;
        }
    }
    cout << endl;
    return 0;
}