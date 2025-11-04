#include <iostream>
#include <fstream>

using namespace std;
int n;
int i;
int a[80000];

int main() {
    cin >> n;
    for (i = 0; i < n; i++){
        a[i] = i+1;
        if (i > 1) swap(a[i], a[i/2]);
    }
    for (i = 0; i<n; i++){
        cout << a[i] << " ";
    }
    return 0;
}