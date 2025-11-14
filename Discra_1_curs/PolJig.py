n = int(input())
mapa = [list(map(str, input().split())) for _ in range(2**n)]
treg = []
treg2 = []
for i in range(2**n):
    treg.append(int(mapa[i][1]))
    treg2.append(int(mapa[i][1]))
for i in range(1,2**n):
    treg2 = list(treg)
    for j in range(2**n-i):
        treg[j] = (treg2[j] + treg2[j+1])%2
    treg = treg[0:-1]
    mapa[i][1] = treg[0]
for i in range(2**n):
    print(mapa[i][0] + " " + str(mapa[i][1]))
