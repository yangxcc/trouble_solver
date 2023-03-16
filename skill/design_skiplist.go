/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-03-15 18:22:33
 * @LastEditTime: 2023-03-16 09:34:28
 */
package skill

import (
	"math/rand"
	"time"
)

// 明天用go实现，避免明天就忘了🤦‍
type Skiplist struct {
	Level int
	Head  *Node
}

func Constructor() Skiplist {
	return Skiplist{Level: 10, Head: &Node{Val: -1, Ns: make([]*Node, 10)}}
}

func (this *Skiplist) Search(target int) bool {
	ne := make([]*Node, this.Level)
	this.find(target, ne)

	return ne[0].Ns[0] != nil && ne[0].Ns[0].Val == target
}

func (this *Skiplist) Add(num int) {
	ne := make([]*Node, this.Level)
	this.find(num, ne)

	node := &Node{Val: num, Ns: make([]*Node, this.Level)}
	for i := 0; i < this.Level; i++ {
		node.Ns[i] = ne[i].Ns[i]
		ne[i].Ns[i] = node

		rand.Seed(time.Now().Unix())
		if rand.Intn(2) == 0 {
			break
		}
	}
}

func (this *Skiplist) Erase(num int) bool {
	ne := make([]*Node, this.Level)
	this.find(num, ne)

	if ne[0].Ns[0] == nil || ne[0].Ns[0].Val != num {
		return false
	}

	for i := 0; i < this.Level; i++ {
		if ne[i].Ns[i] != nil {
			ne[i].Ns[i] = ne[i].Ns[i].Ns[i]
		} else {
			ne[i].Ns[i] = nil
		}
	}

	return true
}

/**
ne[i]表示第i层比target严格小的最大的数
*/
func (this *Skiplist) find(target int, ne []*Node) {
	cur := this.Head
	for i := this.Level - 1; i >= 0; i-- {
		for cur.Ns[i] != nil && cur.Ns[i].Val < target {
			cur = cur.Ns[i]
		}

		ne[i] = cur
	}
}

type Node struct {
	Val int
	// ns[i]表示的是在第i层，刚好比当前节点大的那个节点
	Ns []*Node
}
