var vm = new Vue({
    el:"#dept",
    data:{
        deptlist:{},
        pageNum:1,
        pageSize:5,
        page:{},
        entity:{},
        dept:[],
        depts:{},
    },
    methods:{
        changesize:function(obj){
            this.pageSize=obj;
            this.selectdept();
        },
        paging:function(obj){
            this.pageNum=obj;
            this.selectdept()
        },
        selectdept:function () {
            var _this = this;
            axios.post("../user/selectdept.do?pageNum="+_this.pageNum+"&pageSize="+_this.pageSize).then(function (response) {
            _this.pageNum=response.data.currentPage;
            _this.pageSize=response.data.pageSize;
            _this.deptlist=response.data.list;
            _this.page=response.data;
            console.log(response.data)
            })
        },
        godeptpost:function (id) {
            var _this = this;
            axios.get("../dept/godeptpost.do?id="+id).then(function (response) {
                _this.entity=response.data;
                _this.depts=response.data.depts;
                _this.dept=response.data.dept;
                console.log("-----------"+_this.entity)
                console.log("_______________"+_this.depts)
                console.log("_______________"+_this.dept)
                document.getElementById("orupdept").style.display="block"
            })
        },
        sava:function () {
            var _this=this;
            axios.post("../dept/savepost.do?id="+_this.entity.id,_this.depts).then(function () {
                alert("编辑成功!");
                _this.selectdept();
                document.getElementById("orupdept").style.display="none"
            }).catch(function (err) {
                alert(err);
            });
        }
    }
});
vm.selectdept();