#include <vector>
#include <iostream>
using namespace std;

long long merge(vector<long long>& arr, int left, int mid, int right, long long k1) {
    int n1 = mid - left + 1;
    int n2 = right - mid;

    vector<long long> L(n1), R(n2);

    for (int i = 0; i < n1; ++i)
        L[i] = arr[left + i];
    for (int j = 0; j < n2; ++j)
        R[j] = arr[mid + 1 + j];

    int i = 0, j = 0, k = left;
    long long count = 0;
    
    int r = 0;
    for (int l = 0; l < n1; l++) {
        while (r < n2 && R[r] - L[l] < k1) {
            r++;
        }
        count += (n2 - r);
    }
    // while (l < n1) {
    //     if (R[r]-L[l] >= k1) {
    //         count+=(n1-l);
    //         break;
    //     }
    // }
    // while (r < n2) {
    //     if (R[r]-L[l] >= k1) {
    //         count+=(n2-r);
    //         break;
    //     }
    // }
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k++] = L[i++];
        } else {
            arr[k++] = R[j++];
        }
    }
    while (i < n1) {
        arr[k++] = L[i++];
    }
    while (j < n2) {
        arr[k++] = R[j++];
    }
    return count;
}

long long mergeSort(vector<long long>& arr, int left, int right, long long k1) {
    long long count = 0;
    if (left < right) {
        int mid = left + (right - left) / 2;
        count += mergeSort(arr, left, mid, k1);
        count += mergeSort(arr, mid + 1, right, k1);
        count += merge(arr, left, mid, right, k1);
    }
    return count;
}

int main() {
    long long n, k;
    cin >> n >> k;
    vector<long long> arr(n);
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }
    vector<long long> pref(n+1);
    pref[0] = 0;
    for (int i = 0; i < n; i++) {
        pref[i+1] = pref[i] + arr[i];
    }
    long long count = mergeSort(pref, 0, n, k);
    cout << count;
}
