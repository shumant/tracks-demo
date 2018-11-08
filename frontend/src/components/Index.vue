<template>
    <div>
        Index Component
        <h1>Items</h1>

        <table class="table table-hover">
            <thead>
            <tr>
                <td>ID</td>
                <td>Item Name</td>
                <td>Item description</td>
                <td>Actions</td>
            </tr>
            </thead>

            <tbody>
                <tr v-for="item in items">
                    <td>{{ item.id }}</td>
                    <td>{{ item.name }}</td>
                    <td>{{ item.description }}</td>
                    <td><router-link :to="{name: 'Edit', params: { item: item }}" class="btn btn-primary">Edit</router-link></td>
                    <td><button class="btn btn-danger"  v-on:click="deleteItem(item)">Delete</button></td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import {APIService} from '../APIService';

const apiService = new APIService();

    export default {
        data(){
            return{
                items: []
            }
        },

        created: function()
        {
            this.fetchItems();
        },

        methods: {
            fetchItems()
            {
              apiService.getTracks().then((response) => {
                  this.items = response.data;
              });
            },
            deleteItem(id)
            {
              apiService.deleteTrack(id).then((response) => {
                  this.fetchItems();
              })
            }
        }
    }
</script>
