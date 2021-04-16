var vm = new Vue({
    el:'#userdiv',
    data:{
        userlist:[]
    },
    methods:{
        getUserList:function () {
            var _this = this;
            axios.get("../user/getUserList.do").then(function (response) {
                _this.userlist = response.data;
            });
        }
    }
});
vm.getUserList();
