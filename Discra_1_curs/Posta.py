import itertools
def LineFunction(n,line):
    treg = []
    treg2 = []
    ans = []
    ans.append(line[0])
    for i in range(2**n):
        treg.append(int(line[i]))
        treg2.append(int(line[i]))
    for i in range(1,2**n):
        treg2 = list(treg)
        for j in range(2**n-i):
            treg[j] = (treg2[j] + treg2[j+1])%2
        treg = treg[0:-1]
        ans.append(treg[0])
    i = 0
    for values in itertools.product([0, 1], repeat=n):
        if values.count(1) > 1 and ans[i] == 1:
            return True
        i+=1
    return False



n = int(input())
s = [0,0,0,0,0]
mapa = []
for i in range(n):
    mapa.append(list(map(str,input().split())))
    if int(mapa[i][1][0]) == 1:
        s[0] = 1 # not save 0
    if int(mapa[i][1][-1]) == 0:
        s[1] = 1 # not save 1
    arity = int(mapa[i][0])
    total = 2 ** arity
    for j in range(total):
        if mapa[i][1][j] == mapa[i][1][total - 1 - j]:
            s[2] = 1
            break
    arity = int(mapa[i][0])
    table = [int(c) for c in mapa[i][1]]
    total = 1 << arity
    for a in range(total):
        for b in range(total):
            if (a | b) == b and table[a] > table[b]:
                s[3] = 1
                break
        if s[3]:
            break
    if LineFunction(int(mapa[i][0]),mapa[i][1]):
        s[4] = 1 # not line

# print(s)
if sum(s) == 5:
    print("YES")
else:
    print("NO")