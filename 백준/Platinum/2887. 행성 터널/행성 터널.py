import heapq
import sys
input = sys.stdin.readline

def find_parent(parent, x) :
  if parent[x] != x :
    parent[x] = find_parent(parent, parent[x])
  return parent[x]

def union_parent(parent, i,j) :
  a = find_parent(parent, i)
  b = find_parent(parent, j)
  if a < b :
    parent[b] = a
  else :
    parent[a] = b

N = int(input())
x_arr = []
y_arr = []
z_arr = []
q = []
parent = [i for i in range(N)]

for i in range(N) :
  x,y,z = map(int, input().split())
  x_arr.append((x,i))
  y_arr.append((y,i))
  z_arr.append((z,i))

x_arr.sort()
y_arr.sort()
z_arr.sort()

for i in range(N-1) :
  heapq.heappush(q,(x_arr[i+1][0] - x_arr[i][0], x_arr[i][1], x_arr[i+1][1]))
  heapq.heappush(q,(y_arr[i+1][0] - y_arr[i][0], y_arr[i][1], y_arr[i+1][1]))
  heapq.heappush(q,(z_arr[i+1][0] - z_arr[i][0], z_arr[i][1], z_arr[i+1][1]))

answer = 0
while q :
  cost,i,j = heapq.heappop(q)
  if find_parent(parent,i) != find_parent(parent,j) :
    union_parent(parent, i, j)
    answer += cost

print(answer)