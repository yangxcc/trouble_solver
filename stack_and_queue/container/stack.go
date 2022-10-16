package container

type containertype interface {
	string | int
}

type Stack[T containertype] struct {
	list []T
}

func (s *Stack[T]) Empty() bool {
	return len(s.list) == 0
}

func (s *Stack[T]) Push(item T) {
	s.list = append(s.list, item)
}

func (s *Stack[T]) Pop() T {
	if s.Empty() {
		return T(rune(-1))
	}
	lth := len(s.list)
	top := s.list[lth-1]
	s.list = s.list[0 : lth-1]
	return top
}

// Top == Peek
func (s *Stack[T]) Top() T {
	if s.Empty() {
		return T(rune(-1))
	}
	return s.list[len(s.list)-1]
}

func (s *Stack[T]) Length() int {
	return len(s.list)
}
