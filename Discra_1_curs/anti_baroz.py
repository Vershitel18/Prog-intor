n = input()
A = list(n)
for i in range(len(n)-1):
    A = sorted(A)
    for j in range(len(n)):
        A[j] = n[j] + A[j]
A = sorted(A)
print(A[0])

