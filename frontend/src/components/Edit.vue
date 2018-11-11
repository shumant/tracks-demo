<template>
  <div class="container">
    Edit Component
    <div class="card">
      <div class="card-header">
        <h3>Edit Item</h3>
      </div>
      <div class="card-body">
        <form v-on:submit.prevent="updateItem">
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
            <input type="text" class="form-control" v-model="item.length.value"/>
          </div>
          <div class="form-group">
            <input type="submit" class="btn btn-primary" value="Update Track"/>
          </div>
        </form>
      </div>
    </div>
    <div class="card">
      <div class="card-header">
        <h3>Cars on track</h3>
      </div>
      <div class="card-body">

        <button @click="createCar()" class="btn btn-primary">Add Car</button>


        <table class="table table-hover">
          <thead>
          <tr>
            <td>ID</td>
            <td>Code</td>
            <td>Transmission</td>
            <td>Art. intel.</td>
            <td>Max speed, mps</td>
            <td>Actions</td>
          </tr>
          </thead>

          <tbody>


          <tr v-if="openCarCreation">
            <td></td>
            <td>
              <input type="text" v-model="creatingCar.code"/>
            </td>
            <td>
              <input type="text" v-model="creatingCar.transmission"/>
            </td>
            <td>
              <input type="text" v-model="creatingCar.ai"/>
            </td>
            <td>
              <input type="text" v-model="creatingCar.max_speed.value"/> MPS
            </td>

            <td>
              <button @click="saveCar(creatingCar)" class="btn btn-primary">save</button>
            </td>
            <td>
              <button @click="cancelCarCreation()" class="btn btn-warning">cancel</button>
            </td>
          </tr>


          <tr v-for="car in cars">
            <td>{{ car.id }}</td>
            <td v-if="!thisCarIsEdited(car)">{{ car.code }}</td>
            <td v-if="thisCarIsEdited(car)">
              <input type="text" v-model="car.code"/>
            </td>
            <td v-if="!thisCarIsEdited(car)">{{ car.transmission }}</td>
            <td v-if="thisCarIsEdited(car)">
              <input type="text" v-model="car.transmission"/>
            </td>
            <td v-if="!thisCarIsEdited(car)">{{ car.ai }}</td>
            <td v-if="thisCarIsEdited(car)">
              <input type="text" v-model="car.ai"/>
            </td>
            <td v-if="!thisCarIsEdited(car)">{{ car.max_speed.value }} {{ car.max_speed.unit }}</td>
            <td v-if="thisCarIsEdited(car)">
              <input type="number" v-model="car.max_speed.value"/> {{ car.max_speed.unit }}
            </td>


            <td>
              <div v-if="!thisCarIsEdited(car)">
                <button @click="editCar(car)" class="btn btn-primary">edit</button>
              </div>
              <div v-if="thisCarIsEdited(car)">
                <button @click="updateCar(car)" class="btn btn-warning">update</button>
              </div>
            </td>
            <td>
              <div>
                <button @click="deleteCar(car)" class="btn btn-danger">delete</button>
              </div>
            </td>
          </tr>
          </tbody>
        </table>
      </div>

    </div>
  </div>
</template>
<script>
  import {APIService} from '../APIService';

  const apiService = new APIService();
  export default {
    data() {
      return {
        item: {
          length: {}
        },
        editMode: false,
        editedCar: {
          max_speed: {}
        },
        cars: [],
        creatingCar: {
          max_speed: {}
        },
        openCarCreation: false
      }
    },

    created: function () {
      this.getItem();
      this.getCars();
    },

    methods: {
      getItem() {
        this.item = this.$route.params.item;
      },

      updateItem() {
        this.item.length.unit = "km";
        apiService.updateTrack(this.item).then((response) => {
          this.$router.push({name: 'Index'});
        });
      },

      getCars() {
        apiService.getCars(this.item.id).then((response) => {
          this.cars = response.data
        })
      },

      createCar() {
        this.openCarCreation = true;
        this.creatingCar = {
          max_speed: {}
        }
      },

      saveCar(car) {
        apiService.createCar(this.item.id, car).then((response) => {
          this.getCars();
          this.creatingCar = {
            max_speed: {}
          };
          this.openCarCreation = false;
        })
      },
      cancelCarCreation() {
        this.creatingCar = {
          max_speed: {}
        };
        this.openCarCreation = false;
      },
      editCar(car) {
        this.editedCar = car;
      },
      updateCar(car) {
        apiService.updateCar(car).then((response) => {
          this.getCars();
          this.editedCar = {};
        })
      },
      deleteCar(car) {
        apiService.deleteCar(car.id).then((response) => {
          this.getCars();
        })
      },
      thisCarIsEdited(car) {
        return this.editedCar == car
      }
    }
  }
</script>
<style>
  [v-cloak] {
    display: none;
  }

  .edit {
    display: none;
  }

  .editing .edit {
    display: block
  }

  .editing .view {
    display: none;
  }

</style>
