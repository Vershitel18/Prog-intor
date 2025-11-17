n = int(input())
A = list(map(int, input().split()))
ans = 0
while len(A) > 1:
    A = sorted(A)
    a_0 = A[0]
    a_1 = A[1]
    sumi = a_0 + a_1
    A.remove(a_0)
    A.remove(a_1)
    A.append(sumi)
    ans += sumi
print(ans)
