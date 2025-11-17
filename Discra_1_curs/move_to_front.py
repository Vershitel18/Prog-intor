n = input()
A = list()
for i in range(len(n)):
    if A.count(n[i]) == 0:
        A.append(n[i])
A = sorted(A)
ans = list()
for i in range(len(n)):
    ans.append(A.index(n[i]) + 1)
    g = n[i]
    A.remove(g)
    F = list()
    F.append(n[i])
    A = F + A
print(*ans)

