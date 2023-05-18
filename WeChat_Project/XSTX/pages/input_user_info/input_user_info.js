// pages/input_user_info/input_user_info.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
  },
  get_all_input: function(e){
    if(e.detail.value.name != null && e.detail.value.name != '' && e.detail.value.age != null && e.detail.value.age != '' && e.detail.value.phone != null && e.detail.value.phone != '' && e.detail.value.address != null && e.detail.value.address != ''){
      // 输入个人信息数据是合法的，将用户填写数据先放入 ApplicationContext 域缓存
      app.globalData.name = e.detail.value.name
      app.globalData.age = e.detail.value.age
      app.globalData.phone = e.detail.value.phone
      app.globalData.address = e.detail.value.address
      wx.request({
        url: 'https://publicservice.mynatapp.cc/pages/updateUserInfo',
        data: {
          openid: app.globalData.openid,
          name: e.detail.value.name,
          age: e.detail.value.age,
          phone: e.detail.value.phone,
          address:e.detail.value.address
        },
        method: 'POST',
        dataType: 'json',
        success(resp){
          wx.navigateBack({
          })
        }
      })
    }else{
      // 输入个人信息数据不合法
      wx.showToast(
        {
          title: '格式有误！',
          icon: "error",
          duration: 800
        }
      );
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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
      name: app.globalData.name,
      age: app.globalData.age,
      phone: app.globalData.phone,
      address: app.globalData.address
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