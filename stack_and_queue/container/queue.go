package container

type Queue[T containertype] struct {
	list []T
}

func (q *Queue[T]) Empty() bool {
	return len(q.list) == 0
}

func (q *Queue[T]) Push(item T) {
	q.list = append(q.list, item)
}

func (q *Queue[T]) Pop() T {
	if q.Empty() {
		return T(rune(-1))
	}
	val := q.list[0]
	q.list = q.list[1:]
	return val
}

func (q *Queue[T]) Top() T {
	if q.Empty() {
		return T(rune(-1))
	}

	return q.list[0]
}

func (q *Queue[T]) Length() int {
	return len(q.list)
}
