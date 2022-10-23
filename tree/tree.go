package tree

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

type Node struct {
	Val   int
	Left  *Node
	Right *Node
	Next  *Node
}

type containerType interface {
	TreeNode | Node
}

type Stack[T containerType] struct {
	list []*T
}

func (s *Stack[T]) Empty() bool {
	return len(s.list) == 0
}

func (s *Stack[T]) Push(item *T) {
	s.list = append(s.list, item)
}

func (s *Stack[T]) Pop() *T {
	if s.Empty() {
		return nil
	}
	lth := len(s.list)
	top := s.list[lth-1]
	s.list = s.list[0 : lth-1]
	return top
}

// Top == Peek
func (s *Stack[T]) Top() *T {
	if s.Empty() {
		return nil
	}
	return s.list[len(s.list)-1]
}

func (s *Stack[T]) Length() int {
	return len(s.list)
}

// ================= 队列 =================
type Queue[T containerType] struct {
	list []*T
}

func (q *Queue[T]) Empty() bool {
	return len(q.list) == 0
}

func (q *Queue[T]) Push(item *T) {
	q.list = append(q.list, item)
}

func (q *Queue[T]) Pop() *T {
	if q.Empty() {
		return nil
	}
	val := q.list[0]
	q.list = q.list[1:]
	return val
}

func (q *Queue[T]) Top() *T {
	if q.Empty() {
		return nil
	}

	return q.list[0]
}

func (q *Queue[T]) Length() int {
	return len(q.list)
}
