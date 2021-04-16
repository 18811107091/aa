var vm = new Vue({
   el:"#menu",
   data:{
       menulist:[],
       pid:1,
       savemenu:{}
   },
    methods:{
        selectmenu:function (pid) {
            this.pid = pid;
            var _this = this;
            axios.get("../user/selectmenu.do?pid="+pid).then(function (response) {
                _this.menulist=response.data;
            })
        },
        goadd:function () {
            //添加完成之后将这个数组里的值删除，即重新赋值为空
            this.savemenu={};
            document.getElementById("saveorupmenu").style.display="block";
        },
        addorup:function () {
            this.savemenu.pid=this.pid;
            var _this = this;
            axios.post("../user/addorupmenu.do",_this.savemenu).then(function (response) {
                alert("编辑成功！")
                document.getElementById("saveorupmenu").style.display="none";
            }).catch(function (err) {
                alert(err);
            })
        },
        del:function (id) {
            var _this = this;
            axios.get("../user/delmenu.do?id="+id).then(function (response) {
                alert("删除成功")
                _this.selectmenu(_this.pid);
            }).catch(function (err) {
                alert(err);
            })
        },
        up:function (id) {
        var _this = this;
        axios.get("../user/selectmenuByid?id="+id).then(function (response) {
            _this.savemenu=response.data;
            document.getElementById("saveorupmenu").style.display="block";
        })
        }
    }
});
vm.selectmenu(1);