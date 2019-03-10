<template>
  <div>
    <h1 class="title">Customers</h1>
    <h1 class="email">{{userEmail}}</h1>
    <section class="customerapp">
      <div v-if="loading">
        <h1 class="loading">Loading...</h1>
      </div>
      <div v-else>
        <header class="header">
          <input class="new-customer"
                 autofocus autocomplete="off"
                 :placeholder="this.inputPlaceholder"
                 v-model="newCustomer"
                 @keyup.enter="addCustomer">
        </header>
        <section class="main" v-show="customers.length" v-cloak>
          <input class="toggle-all" type="checkbox" v-model="allDone">
          <ul class="customer-list">
            <li v-for="customer in filteredCustomers"
                class="customer"
                :key="customer.id"
                :class="{ completed: customer.completed, editing: customer == editedCustomer }">
              <div class="view">
                <input class="toggle" type="checkbox" v-model="customer.completed" @change="completeCustomer(customer)">
                <label @dblclick="editCustomer(customer)">{{ customer.title }}</label>
                <button class="destroy" @click="removeCustomer(customer)"></button>
              </div>
              <input class="edit" type="text"
                     v-model="customer.title"
                     v-customer-focus="customer == editedCustomer"
                     @blur="doneEdit(customer)"
                     @keyup.enter="doneEdit(customer)"
                     @keyup.esc="cancelEdit(customer)">
            </li>
          </ul>
        </section>
        <footer class="footer" v-show="customers.length" v-cloak>
          <span class="customer-count">
            <strong>{{ remaining }}</strong> {{ remaining | pluralize }} left
          </span>
          <ul class="filters">
            <li><a href="#/all" @click="setVisibility('all')" :class="{ selected: visibility == 'all' }">All</a></li>
            <li><a href="#/active" @click="setVisibility('active')" :class="{ selected: visibility == 'active' }">Active</a></li>
            <li><a href="#/completed" @click="setVisibility('completed')" :class="{ selected: visibility == 'completed' }">Completed</a></li>
          </ul>
          <button class="clear-completed" @click="removeCompleted" v-show="customers.length > remaining">
            Clear completed
          </button>
        </footer>
      </div>
    </section>
    <div v-if="error" class="error" @click="handleErrorClick">
      ERROR: {{this.error}}
    </div>
  </div>
</template>

<script>

  // visibility filters
  let filters = {
    all: function (customers) {
      return customers
    },
    active: function (customers) {
      return customers.filter(function (customer) {
        return !customer.completed
      })
    },
    completed: function (customers) {
      return customers.filter(function (customer) {
        return customer.completed
      })
    }
  }

  // app Vue instance
  const Customers = {
    name: 'Customers',
    props: {
      activeUser: Object
    },

    // app initial state
    data: function() {
      return {
        customers: [],
        newCustomer: '',
        editedCustomer: null,
        visibility: 'all',
        loading: true,
        error: null,
      }
    },

    mounted() {
      // inject some startup data
      this.customers = [{title: 'Drink coffee', completed:false},{title: 'Write REST API', completed:false}];
      // hide the loading message
      this.loading = false;
    },

    // computed properties
    // http://vuejs.org/guide/computed.html
    computed: {
      filteredCustomers: function () {
        return filters[this.visibility](this.customers)
      },
      remaining: function () {
        return filters.active(this.customers).length
      },
      allDone: {
        get: function () {
          return this.remaining === 0
        },
        set: function (value) {
          this.customers.forEach(function (customer) {
            customer.completed = value
          })
        }
      },
      userEmail: function () {
        return this.activeUser ? this.activeUser.email : ''
      },
      inputPlaceholder: function () {
        return this.activeUser ? this.activeUser.given_name + ', what needs to be done?' : 'What needs to be done?'
      }
    },

    filters: {
      pluralize: function (n) {
        return n === 1 ? 'item' : 'items'
      }
    },

    // methods that implement data logic.
    // note there's no DOM manipulation here at all.
    methods: {

      addCustomer: function () {
        var value = this.newCustomer && this.newCustomer.trim()
        if (!value) {
          return
        }

        this.customers.push({
          title: value,
          completed: false
        });

        this.newCustomer = ''
      },

      setVisibility: function(vis) {
        this.visibility = vis
      },

      completeCustomer (customer) {
      },

      removeCustomer: function (customer) { // notice NOT using "=>" syntax
        this.customers.splice(this.customers.indexOf(customer), 1)
      },

      editCustomer: function (customer) {
        this.beforeEditCache = customer.title
        this.editedCustomer = customer
      },

      doneEdit: function (customer) {
        if (!this.editedCustomer) {
          return
        }

        this.editedCustomer = null
        customer.title = customer.title.trim()

        if (!customer.title) {
          this.removeCustomer(customer)
        }
      },

      cancelEdit: function (customer) {
        this.editedCustomer = null
        customer.title = this.beforeEditCache
      },

      removeCompleted: function () {
        this.customers = filters.active(this.customers)
      },

      handleErrorClick: function () {
        this.error = null;
      },
    },

    // a custom directive to wait for the DOM to be updated
    // before focusing on the input field.
    // http://vuejs.org/guide/custom-directive.html
    directives: {
      'customer-focus': function (el, binding) {
        if (binding.value) {
          el.focus()
        }
      }
    }
  }

  export default Customers
</script>

<style>
  [v-cloak] { display: none; }
</style>