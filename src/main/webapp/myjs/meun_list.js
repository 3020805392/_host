var vm = new Vue({
    el:'#meundiv',
    data:{
        meunlist:[],
        pid:1,
        entity:{}
    },
    methods:{
        getMeunListByPid:function (pid) {
            this.pid = pid;
            var _this = this;
            axios.get('../meun/getMeunListByPid.do?pid='+pid).then(function (response) {
                _this.meunlist = response.data;
            });
        },
        toAddMeun:function () {
            this.entity={};
            document.getElementById("meunupdatediv").style.display="block";
        },
        saveMeun:function () {
            this.entity.pid = this.pid;
            var _this = this;
            axios.post("../meun/saveMeun.do",_this.entity).then(function (response) {
                alert("编辑成功")
                _this.getMeunListByPid(_this.pid);
                document.getElementById("meunupdatediv").style.display="none";
            }).catch(function (ree) {
                alert(ree);
            })
        },
        deleteMeunById:function (id) {
            var _this = this;
            axios.get("../meun/deleteMeunById.do?id="+id).then(function (response) {
                alert("删除成功")
                _this.getMeunListByPid(_this.pid);
                document.getElementById("meunlistdiv").style.display="none";
            }).catch(function (ree) {
                alert(ree);
            });
        }
    }
});
vm.getMeunListByPid(1);
