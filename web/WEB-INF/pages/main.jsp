<%--
  Created by IntelliJ IDEA.
  User: 84623
  Date: 13/4/2023
  Time: 下午2:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>公益服务平台管理系统 - 主页</title>
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!-- 导航 BAR 开始 -->
<div id="bar">
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-menu-item index="1"><p style="display: none;" id="admin_name">欢迎您，管理员：{{admin_name}}</p></el-menu-item>
        <el-menu-item index="2"><a href="/pages/AdminLogout" target="_blank">安全登出</a></el-menu-item>
    </el-menu>
</div>
<!-- 导航 BAR 结束 -->

<!-- 表格样式开始 -->
<div id="Mytable">
    <template>
        <el-table
                :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
                style="width: 100%">
            <!-- 活动缩略图 -->
            <el-table-column label="" prop="icon_url">
                <template v-slot="scope">
                    <el-image
                            style="width: 100px; height: 100px"
                            :src="scope.row.a_icon">
                    </el-image>
                    <el-image
                            style="width: 100px; height: 100px"
                            :src="scope.row.a_image">
                    </el-image>
                </template>

            </el-table-column>

            <!-- 活动名称 -->
            <el-table-column
                    label="活动名称"
                    prop="a_name">
            </el-table-column>

            <!-- 活动日期 -->
            <el-table-column
                    label="活动日期"
                    prop="formatDate">
            </el-table-column>

            <!-- 活动主办方 -->
            <el-table-column
                    label="主办方"
                    prop="a_party">
            </el-table-column>

            <!-- 活动简介 -->
            <el-table-column
                    label="活动简介"
                    prop="a_lore">
            </el-table-column>

            <el-table-column
                    align="right">
                <template slot="header" slot-scope="scope">
                    <button type="button"
                            class="btn btn-primary"
                            @click="createActivity">新增活动</button>

                </template>
                <template slot-scope="scope">
                    <button type="button"
                            class="btn btn-primary btn-sm"
                            data-bs-toggle="modal"
                            data-bs-target="#activityChangeModal"
                            @click="handleEdit(scope.row)">修改</button>
                    <button
                            type="button"
                            class="btn btn-danger btn-sm"
                            @click="handleDelete(scope.row.a_id)">删除</button>
                    <button type="button"
                            class="btn btn-primary btn-sm"
                            @click="handleCheck(scope.row)">查看详情</button>
                    <button type="button"
                            class="btn btn-primary btn-sm"
                            @click="handleGetSignUpInfo(scope.row.a_id)">获取报名信息</button>
                </template>
            </el-table-column>

        </el-table>
    </template>
    <!-- 删除活动提示框 -->
    <div class="modal" id="deleteConfirmModal">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <!-- 模态框头部 -->
                <div class="modal-header">
                    <h4 class="modal-title">提示：</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <!-- 模态框内容 -->
                <div class="modal-body">
                    <h4 class="modal-title">确认删除该活动？</h4>
                </div>
                <!-- 模态框底部 -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-light" data-bs-dismiss="modal" style="border-width: 1px">取消</button>
                    <button type="button" class="btn btn-danger" @click="delete_by_a_id">确认</button>
                </div>
            </div>
        </div>
    </div>

    <!-- 活动详情模态窗口 -->
    <div class="modal" id="activityDetail">
        <div class="modal-dialog modal-fullscreen">
            <div class="modal-content">
                <!-- 模态框头部 -->
                <div class="modal-header">
                    <h4 class="modal-title">活动信息</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <!-- 模态框内容 -->
                <div class="modal-body">
                    <div class="container mt-3" style="margin-top: 35px">
                        <h4>活动名称：</h4>
                        <h6 style="margin-bottom: 35px">{{detail_a_name}}</h6>
                        <h4>活动日期：</h4>
                        <h6 style="margin-bottom: 35px">{{detail_formatDate}}</h6>
                        <h4>活动简介：</h4>
                        <h6 style="margin-bottom: 35px">{{detail_lore}}</h6>
                        <h4>活动主办方：</h4>
                        <h6 style="margin-bottom: 35px">{{detail_a_party}}</h6>
                        <h4>活动主办方联系方式：</h4>
                        <h6 style="margin-bottom: 35px">{{detail_a_phone}}</h6>
                        <h4>活动内容描述：</h4>
                        <h6 style="margin-bottom: 35px">{{detail_a_describe}}</h6>
                        <h4>活动图片：</h4>
                        <el-image style="width: 100%;height: 100%;margin-top: 15px"
                            :src="detail_a_icon" :fit="fit" :preview-src-list="bigIMG_a_icon"
                        ></el-image>
                        <el-image style="width: 100%;height: 100%;margin-top: 15px"
                            :src="detail_a_image" :fit="fit" :preview-src-list="bigIMG_a_image"
                        ></el-image>
                    </div>
                </div>
                <!-- 模态框底部 -->
                <div class="modal-footer" style="display: flow;margin-top: 10px">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 表格样式结束 -->

<!-- FORM 表单样式开始 -->
<!-- 模态框 -->
<div class="modal" id="activityChangeModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div id="addActivityForm">
            <!-- 模态框头部 -->
            <div class="modal-header">
                <h4 class="modal-title">活动信息编辑：</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <!-- 模态框内容 -->
            <div class="modal-body">
                    <el-form ref="form" :model="form" label-width="80px" :label-position="labelPosition">
                        <el-form-item label="活动名称：（48字以内）">
                            <el-input
                                    v-model="form.a_name"
                                    maxlength="48"
                                    minlength="1"
                                    show-word-limit
                            ></el-input>
                        </el-form-item>
                        <el-form-item label="活动缩略图上传：">
                            <input type="file" id="icon_imgFile">
                            <el-button type="primary" @click="icon_fileUpload">上传<i class="el-icon-upload el-icon--right"></i></el-button>
                        </el-form-item>
                        <el-form-item label="活动时间：">
                            <el-col :span="11">
                                <el-date-picker type="date" placeholder="选择日期" v-model="form.a_date" style="width: 100%;"></el-date-picker>
                            </el-col>
                        </el-form-item>
                        <el-form-item label="活动简介：（96字以内）">
                            <el-input
                                    v-model="form.a_lore"
                                    maxlength="96"
                                    minlength="1"
                                    show-word-limit
                            ></el-input>
                        </el-form-item>
                        <el-form-item label="活动主办方：（36字以内）">
                            <el-input
                                    v-model="form.a_party"
                                    maxlength="36"
                                    minlength="1"
                                    show-word-limit
                            ></el-input>
                        </el-form-item>
                        <el-form-item label="主办方联系方式：（负责人手机号码）">
                            <el-input
                                    v-model="form.a_phone"
                                    maxlength="11"
                                    minlength="1"
                                    show-word-limit
                            ></el-input>
                        </el-form-item>
                        <el-form-item label="活动主要描述：（255字以内）">
                            <el-input
                                    type="textarea"
                                    :rows="6"
                                    placeholder="请输入活动内容描述"
                                    v-model="form.a_describe"
                                    maxlength="255"
                                    minlength="1"
                                    show-word-limit
                            ></el-input>
                        </el-form-item>

                        <el-form-item label="活动宣传图上传：">
                            <input type="file" id="image_imgFile">
                            <el-button type="primary" @click="image_fileUpload">上传<i class="el-icon-upload el-icon--right"></i></el-button>
                        </el-form-item>
                        <el-form-item label="已上传活动图片：" class="block">
                            <el-image
                                style="width: 150px;height: 150px"
                                :src="form.a_icon"
                                :fit="fit"
                                :preview-src-list="a_icon"
                            ></el-image>
                            <el-image
                                style="width: 150px;height: 150px"
                                :src="form.a_image"
                                :fit="fit"
                                :preview-src-list="a_image"
                            ></el-image>
                        </el-form-item>
                    </el-form>
                    <p style="color: red" id="uploadActivitymsg">{{upload_msg}}</p>
            </div>
                <!-- 模态框底部 -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" @click="SubmitActivity">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- FORM 表单样式结束 -->

<!-- 活动报名详情 模态框 -->
<div class="modal fade" id="activity_detail_signUpInformationModal">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">

            <!-- 模态框头部 -->
            <div class="modal-header">
                <h4 class="modal-title">活动报名详情</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <!-- 模态框内容 -->
            <div class="modal-body">
                <!-- 表格样式开始 -->
                <div id="activity_detail_signUpInformation">
                    <template>
                        <el-table
                                :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
                                style="width: 100%">
                            <!-- 用户姓名 -->
                            <el-table-column label="用户姓名" prop="name">
                            </el-table-column>

                            <!-- 用户联系方式 -->
                            <el-table-column
                                    label="联系方式"
                                    prop="phone">
                            </el-table-column>

                            <!-- 用户所属单位 -->
                            <el-table-column
                                    label="所属单位"
                                    prop="address">
                            </el-table-column>

                        </el-table>
                    </template>
                </div>
            </div>

            <!-- 模态框底部 -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">关闭</button>
            </div>

        </div>
    </div>
</div>




</body>

<script src="${pageContext.request.contextPath}/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/js/axios-0.18.0.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/element-ui/lib/index.js"></script>
<!-- bootstrap 核心 js 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
<!-- bootstrap 核心 css 文件 -->
<link href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/element-ui/lib/theme-chalk/index.css">

<script>

    // 首页顶部导航栏模块
    var V1 = new Vue({
        el: "#bar",
        data(){
            return {
                activeIndex: '1',
                admin_name: ''
            };
        },
        mounted(){
            var that = this;
            // 发送异步请求，获取 Session 域中的 admin_name
            axios({
                method: "get",
                url:"getAdminName",
            }).then(function (resp){
                document.getElementById("admin_name").style.display = "block";
                that.admin_name = resp.data;
            })
        },
        methods: {

        }
    });


    // 首页活动列表展示模块
    var V2 = new Vue({
        el: "#Mytable",
        mounted() {
            this.getAllbriefInfo();
        },
        data() {
            return {
                tableData: [
                ],
                search: '',
                delete_a_id: '',
                detail_a_name: '',
                detail_a_party: '',
                detail_a_icon: '',
                detail_a_image: '',
                detail_formatDate: '',
                detail_lore: '',
                detail_a_phone: '',
                detail_a_describe: '',
                fit: 'cover',
                bigIMG_a_icon: [],
                bigIMG_a_image: [],
                queryInfo_a_id: ''
            }
        },
        methods: {
            handleCheck(rowData){
                this.detail_a_name = rowData.a_name;
                this.detail_a_party = rowData.a_party;
                this.detail_a_icon = rowData.a_icon;
                this.detail_a_image = rowData.a_image;
                this.detail_formatDate = rowData.formatDate;
                this.detail_lore = rowData.a_lore;
                this.detail_a_phone = rowData.a_phone;
                this.detail_a_describe = rowData.a_describe;
                this.bigIMG_a_icon = [];
                this.bigIMG_a_image = [];
                this.bigIMG_a_icon.push(rowData.a_icon);
                this.bigIMG_a_image.push(rowData.a_image);
                $('#activityDetail').modal('show');
            },
            handleEdit(rowData) {
                // 完成模态窗口实例数据域的修改
                V3.form.a_id = rowData.a_id;
                V3.form.a_icon = rowData.a_icon;
                V3.form.a_phone = rowData.a_phone;
                V3.form.a_lore = rowData.a_lore;
                V3.form.a_party = rowData.a_party;
                V3.form.a_describe = rowData.a_describe;
                V3.form.a_date = rowData.a_date;
                V3.form.a_image = rowData.a_image;
                V3.form.a_name = rowData.a_name;
                V3.a_icon = [];
                V3.a_image = [];
                V3.a_image.push(rowData.a_image);
                V3.a_icon.push(rowData.a_icon);
                document.getElementById("icon_imgFile").value = '';
                document.getElementById("image_imgFile").value = '';
                // 调用模态框
                $('addActivityForm').modal('show');
            },
            handleDelete(a_id) {
                this.delete_a_id = a_id;
                console.log(this.delete_a_id);
                $('#deleteConfirmModal').modal('show');
            },
            delete_by_a_id(){
                var that = this;
                // 发送异步请求，完成根据 a_id 删除活动操作
                axios({
                    method: "get",
                    url:"deleteActivity?a_id=" + that.delete_a_id,
                }).then(function (resp){
                    if(resp.data == 'delete successed'){
                        // 删除成功
                        // 将暂存的删除活动的 a_id 置空
                        that.delete_a_id = '';
                        $('#deleteConfirmModal').modal('hide');
                        that.getAllbriefInfo();
                    }else{
                        // 删除失败
                        // 将暂存的删除活动的 a_id 置空
                        that.delete_a_id = '';
                        $('#deleteConfirmModal').modal('hide');
                    }
                })
            },
            getAllbriefInfo(){
                var that = this;
                // 发送异步请求，请求服务器的所有活动数据
                axios({
                    method: "get",
                    url:"getAllBrief",
                }).then(function (resp){
                    that.tableData = resp.data;
                    console.log(resp.data)
                })
            },
            createActivity(){
                V3.cleanFormData();
                $('#activityChangeModal').modal('show');
            },

            // 拉取本活动所有报名用户数据
            handleGetSignUpInfo(a_id){
                V2.queryInfo_a_id = a_id;
                // 发送异步请求，请求服务器的所有活动数据
                axios({
                    method: "get",
                    url:"getSignUpInfo?a_id=" +V2.queryInfo_a_id,
                }).then(function (resp){
                    V4.tableData = resp.data;
                    console.log(resp.data);
                    $('#activity_detail_signUpInformationModal').modal('show');
                })
            }
        },
    })


    // 添加活动信息模块
    var V3 = new Vue({
        el: "#addActivityForm",
        data() {
            return {
                form: {
                    // 以下是我自己的数据
                    a_id: '',
                    a_name: '',
                    a_icon: '',
                    a_date: '',
                    a_lore: '',
                    a_party: '',
                    a_phone: '',
                    a_describe: '',
                    a_image: ''
                },
                labelPosition: 'top',
                fit: 'cover',
                // 用于图片大图展示，e-image 大图展示需要的 v-model 元素是列表
                a_icon: [],
                a_image: [],
                upload_msg: ''
            }
        },
        methods: {
            onSubmit() {
                console.log(this.form);
            },
            handleRemove(file) {
                console.log(file);
            },
            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },
            handleDownload(file) {
                console.log(file);
            },
            icon_fileUpload(){
                var that = this;
                var img = document.getElementById("icon_imgFile").files[0];
                console.log(img)
                var formData = new FormData()
                formData.append("file", img)
                axios({
                    method:"post",
                    type: 'POST',
                    url:"imgUpload",
                    data: formData,
                    mimeType:"multipart/form-data",
                }).then(function (resp){
                    that.form.a_icon = resp.data;
                    that.a_icon = [];
                    that.a_icon.push(resp.data);
                })
            },
            image_fileUpload(){
                var that = this;
                var img = document.getElementById("image_imgFile").files[0];
                var formData = new FormData()
                formData.append("file", img)
                axios({
                    method:"post",
                    url:"imgUpload",
                    data: formData,
                    mimeType:"multipart/form-data",
                }).then(function (resp){
                    that.form.a_image = resp.data;
                    that.a_image = [];
                    that.a_image.push(resp.data);
                })
            },
            cleanFormData() {
                this.form.a_id = '';
                this.form.a_name = '';
                this.form.a_icon = '';
                this.form.a_date = '';
                this.form.a_lore = '';
                this.form.a_party = '';
                this.form.a_phone = '';
                this.form.a_describe = '';
                this.form.a_image = '';
                this.a_icon = [];
                this.a_image = [];
                document.getElementById("icon_imgFile").value = '';
                document.getElementById("image_imgFile").value = '';
            },
            SubmitActivity(){
                var that = this;
                if (this.form.a_image != '' && this.form.a_icon != '' && this.form.a_name != '' && this.form.a_lore != '' && this.form.a_date != '' && this.form.a_describe != '' && this.form.a_party != '' && this.form.a_phone != ''){
                    // 已上传所有信息
                    axios({
                        method: "post",
                        url: "EditActivity",
                        data: that.form
                    }).then(function (resp){
                        if(resp.data == "操作成功"){
                            // 模态窗口关闭，并刷新页面数据
                            $('#activityChangeModal').modal('hide');
                            V2.getAllbriefInfo();
                        }else{

                        }
                    })
                }
                else{
                    this.upload_msg = "填写内容不完整，请重新检查后再提交！";
                    setTimeout(function (){
                        that.upload_msg = '';
                    }, 2000);
                }
            }
        }
    })

    // 活动报名详情 Vue 实例
    var V4 = new Vue({
        el: "#activity_detail_signUpInformation",
        data() {
            return {
                tableData: []
            }
        },
        methods: {
        }
    })
</script>
</html>
