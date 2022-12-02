package minimumascii

import "testing"

func TestLCS(t *testing.T) {
	minimumDeleteSum("delete", "leet")
	// [[0 108 209 310 426] [100 0 0 0 0] [201 0 0 0 0] [309 0 0 0 0] [410 0 0 0 0] [526 0 0 0 0] [627 0 0 0 0]]
	// [[0 108 101 101 116] [100 0 0 0 0] [101 0 0 0 0] [108 0 0 0 0] [101 0 0 0 0] [116 0 0 0 0] [101 0 0 0 0]]
}
