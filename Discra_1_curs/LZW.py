n = input()
A = list()
for i in range(len(n)):
    if A.count(n[i]) == 0:
        A.append(n[i])
