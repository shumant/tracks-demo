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
                        <input type="text" class="form-control" v-model="item.description" />
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-primary" value="Update Item"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>
<script>
import {APIService} from '../APIService';

const apiService = new APIService();
export default{
        data(){
            return{
                item:{}
            }
        },

        created: function(){
            this.getItem();
        },

        methods: {
            getItem()
            {
            this.item = this.$route.params.item;
            },

            updateItem()
            {
              apiService.updateTrack(this.item).then((response) => {
                            console.log(response.data)
                            this.$router.push({name: 'Index'});
                        });
            }
        }
    }
</script>
