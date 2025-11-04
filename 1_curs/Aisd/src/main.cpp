#include <iostream>
#include <vector>
#include <string>

using namespace std;

int main() {
    cout << "Hello, C++ in VS Code!" << endl;
    
    vector<string> messages = {"This", "is", "C++"};
    for (const auto& msg : messages) {
        cout << msg << " ";
    }
    cout << endl;
    
    return 0;
}