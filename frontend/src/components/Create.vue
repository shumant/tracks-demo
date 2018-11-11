<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <h3>Add Track</h3>
      </div>
      <div class="card-body">
        <form v-on:submit.prevent="addItem">
          <div class="form-group">
            <label>Item Name:</label>
            <input type="text" class="form-control" v-model="item.name"/>
          </div>
          <div class="form-group">
            <label>Item description:</label>
            <input type="text" class="form-control" v-model="item.description"/>
          </div>
          <div class="form-group">
            <label>Item length, in kilometers:</label>
            <input type="number" class="form-control" v-model="item.length.value"/>
          </div>
          <div class="form-group">
            <input type="submit" class="btn btn-primary" value="Add Track"/>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
  import {APIService} from '../APIService';

  const apiService = new APIService();
  export default {
    components: {
      name: 'AddItem'
    },
    data() {
      return {
        item: {
          length: {}
        }
      }
    },
    methods: {
      addItem() {
        this.item.length.unit = "km"
        apiService.createTrack(this.item).then((response) => {
          console.log(response.data);
          this.$router.push({name: 'Index'});
        });
      }
    }
  }
</script>
