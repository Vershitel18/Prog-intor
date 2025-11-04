# рефлекс, антирефлекс, симметричным, антисимметрычн и транзитивный
n = int(input())
A = [1,1,1,1,1]
B = [1,1,1,1,1]
V = [[0]*n for _ in range(n)]
A1 = [list(map(int, input().split())) for _ in range(n)]
B1 = [list(map(int, input().split())) for _ in range(n)]

for i in range(n):
    if (A1[i][i] != 1):
        A[0] = 0 # refl
    if (A1[i][i] == 1):
        A[1] = 0 # antirefl

for i in range(n):
    for j in range(n):
        if (A1[i][j] != A1[j][i]):
            A[2] = 0 # simm
        if (A1[i][j] == 1 and A1[j][i] == 1 and i != j):
            A[3] = 0 # antisimm

for i in range(n):
    for j in range(n):
        for k in range(n):
            if (A1[i][j] == 1 and A1[j][k] == 1):
                if (A1[i][k] != 1):
                    A[4] = 0 #tranz

for i in range(n):
    if (B1[i][i] != 1):
        B[0] = 0  # refl
    if (B1[i][i] == 1):
        B[1] = 0  # antirefl

for i in range(n):
    for j in range(n):
        if (B1[i][j] != B1[j][i]):
            B[2] = 0  # simm
        if (B1[i][j] == 1 and B1[j][i] == 1 and i != j):
            B[3] = 0  # antisimm

for i in range(n):
    for j in range(n):
        for k in range(n):
            if (B1[i][j] == 1 and B1[j][k] == 1):
                if (B1[i][k] != 1):
                    B[4] = 0  # tranz
for i in range(n):
    for j in range(n):
        for k in range(n):
            if A1[i][j] == 1 and B1[j][k] == 1:
                V[i][k] = 1
print(*A)
print(*B)
for row in V:
    print(' '.join(map(str, row)))
