n = input()
ans = []
ans.append(n)
for i in range (len(n)-1):
    n += n[0]
    n = n[1:]
    ans.append(n)
ans = sorted(ans)
answer = ""
for i in ans:
    answer += i[-1]
print(answer)