const app = new Vue({
  el: '#app',
  data: {
    books: [
      {
        id: 1,
        name: '耗子尾汁',
        date: '2020-12',
        price: 100,
        count: 1

      },
      {
        id: 2,
        name: '二百多斤的大力士',
        date: '2020-10',
        price: 80,
        count: 1,
      },
      {
        id: 3,
        name: '年青人',
        date: '2021-1',
        price: 120,
        count:1
      },
      {
        id: 4,
        name: '偷袭骗',
        date: '2020-1',
        price: 55,
        count: 1
      }
    ]
  },

  methods: {
    increment(index){
      this.books[index].count++
    },
    decrement(index){
      this.books[index].count--
    },
    removeHandle(index){
      this.books.splice(index, 1)
    }
  },

  computed: {
    totalPrice(){
      let totalPrice=0;
      for (let i=0; i<this.books.length; i++){
        totalPrice += this.books[i].price * this.books[i].count;
      }
      return totalPrice;
    }
  },

  filters: {
    showPrice(price){
      return '￥' + price.toFixed(2)
    }
  }
})