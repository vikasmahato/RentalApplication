<template>
  <div id="app">
    <Customers />
    <footer class="info">
      <p>Modified for this tutorial by Vikas</p>
    </footer>
  </div>
</template>

<script>
  import Customers from './components/Customers'
  // app Vue instance
  const app = {
    name: 'app',
    components: {
      Customers
    },
    // app initial state
    data: () => {
      return {
      }
    }
  }

  export default app
</script>

<style>
  [v-cloak] { display: none; }
</style>