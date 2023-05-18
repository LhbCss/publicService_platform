// pages/user/user.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    username:'您还未登录！请登录！',
    userimage_path:'/image/default.png',
    user_openid:''
  },

  denglu_success: function (){
    var that = this;
    wx.request({
      url: 'http://127.0.0.1:8000/api/login/',
      data: {phone: this.data.phone,
      code: this.data.code},
      method: 'POST',
      success: function(res){

      }
    })
  },

  // 登录方法：先获取res.code,再向后台发送code换取openid
  denglu: function(e){
    var that = this;
    wx.login({
      success: (res) => {
        wx.request({
          url: 'https://publicservice.mynatapp.cc/pages/userlogin',
          data: {
            code: res.code // 此处携带 wx.login() 返回的 code
          },
          method:'GET',
          dataType: 'json',
          success(resp){
            console.log("登录成功");
            that.setData({
              user_openid: resp.data // 将得到的 openid 写入页面域数据
            }),
            getApp().globalData.openid = resp.data // 将 openid 写入 ApplicationContext 域数据
          }
        })
      },
    })
    
    wx.getUserProfile({
      desc: 'desc',
      lang: 'zh_CN',
      success: function(res){
        that.setData({
          userimage_path: res.userInfo.avatarUrl,
          username: res.userInfo.nickName
        });
        wx.showToast({
          title: '登录成功！',  // 标题
          icon: 'success',
          duration: 1500
      });
      },
      fail: function(res){
      }
    })
  },
  bindCode: function(e){
    this.setData({code: e.detail.value});
  },
  bindPhone: function(e){
    this.setData({phone:e.detail.value});
  },
  logout: function (){
    app.globalData.openid = '';
    this.setData({
      username: '您还未登录！请登录！',
      userimage_path: '/image/default.png'
    });
    wx.showToast({
      title: '退出登录成功！',  // 标题
      icon: 'success',   // 图标类型，默认success
      duration: 1500   // 提示窗停留时间，默认1500ms
    });

  },
  no_login: function(){
    wx.showToast(
      {
        title: '您还没有登录！',
        icon: "error",
        duration: 500
      }
    );
  },
  goto_personal_info: function (){
    wx.navigateTo({
      url: '/pages/personal_info/personal_info',
    })
  },
  goto_deal: function (){
    wx.navigateTo({
      url: '/pages/deal/deal',
    })
  },
  goto_input_user_info: function(){
    wx.navigateTo({
      url: '/pages/input_user_info/input_user_info',
    })
  },
  getUserInfo: function (){
    var that = this;
    wx.request({
      url: 'https://publicservice.mynatapp.cc/pages/getUserInfo',
      data:{
        openid : that.data.user_openid
      },
      dataType: 'json',
      success(resp){
        if("\"\"" == JSON.stringify(resp.data)){
          // 所查询的用户不存在个人信息，跳转到个人信息填写页面
          wx.navigateTo({
            url: '/pages/input_user_info/input_user_info',
          })
        }else{
          getApp().globalData.name = resp.data.name,
          getApp().globalData.age = resp.data.age,
          getApp().globalData.phone = resp.data.phone,
          getApp().globalData.address = resp.data.address,
          
          wx.navigateTo({
            // 所查询用户个人信息数据不为空，跳转到个人信息展示页面
            url: '/pages/personal_info/personal_info',
          })
        }
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.setNavigationBarTitle({
      title: '用户中心',
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.setData({
      global_name: app.globalData.name
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})