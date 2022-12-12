/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-12 22:02:09
 * @LastEditTime: 2022-12-12 22:07:31
 */
package application

import (
	"fmt"
	"testing"
)

func TestMet(t *testing.T) {
	n := 5
	bookings := [][]int{
		{1, 2, 10},
		{2, 3, 20},
		{2, 5, 25},
	}
	i := corpFlightBookings(bookings, n)
	fmt.Println(i)
}
