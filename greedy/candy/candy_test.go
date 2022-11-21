package candy

import (
	"fmt"
	"testing"
)

func TestCandyMethods(t *testing.T) {
	// ratings := []int{1, 3, 2, 2, 1}
	// ratings := []int{1, 2, 87, 87, 87, 2, 1}
	// ratings := []int{1, 6, 10, 8, 7, 3, 2}
	ratings := []int{1, 3, 4, 5, 2}
	i := candyV3(ratings)
	fmt.Println(i)
}
