using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace EchoServer
{
    public partial class ServerWin : Form
    {
        public ServerWin()
        {
            InitializeComponent();
        }

        Socket listenfd;
        Socket clientfd;
        byte[] readBuff = new byte[1024];
        byte[] sendbuff = new byte[1024];

        private void Listen_btn_Click(object sender, EventArgs e)
        {
            //套接字
            listenfd = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            //绑定
            IPAddress ipadr = IPAddress.Parse("192.168.196.157");
            IPEndPoint ipep = new IPEndPoint(ipadr, 2000);
            listenfd.Bind(ipep);
            //监听
            listenfd.Listen(0);
            Dialog.Items.Add("Server successfully started");
            listenfd.BeginAccept(AcceptCallback, listenfd);

        }

        private void Send_btn_Click(object sender, EventArgs e)
        {
            if (System.Convert.ToDouble(textBox1.Text) < -180 || System.Convert.ToDouble(textBox1.Text) > 180)
            {
                Console.WriteLine("Input angle is in the range [-180,180]");
                return;
            }
            double drad = (360-System.Convert.ToDouble(textBox1.Text)) * 3.14 / 180;
            string rad = drad.ToString();

            byte[] sendbuff = System.Text.Encoding.Default.GetBytes(rad+"\r");
            clientfd.BeginSend(sendbuff,0,sendbuff.Length,0,SendCallback, clientfd);
            SetProgressDelegate setprogress = new SetProgressDelegate(SetProgress);
            this.Invoke(setprogress, new object[] { "Server：Moveing in the " + textBox1.Text+ "° direction", "" });
        }


        public delegate void SetProgressDelegate(String str,String name);

        public void SetProgress(String str, String name)
        {
            Dialog.Items.Add(str);
            if(name!=null)
                Online.Items.Add(name);
        }

        public void AcceptCallback(IAsyncResult ar)
        {
            try
            {
                Socket listenfd = (Socket)ar.AsyncState;
                clientfd= listenfd.EndAccept(ar);

                SetProgressDelegate setprogress = new SetProgressDelegate(SetProgress);
                this.Invoke(setprogress, new object[] { "Client successfully connected", "Elevenoo" });

                clientfd.BeginReceive(readBuff, 0, 1024, 0, ReceiveCallback, clientfd);

            }
            catch (Exception e)
            {
                Console.WriteLine(e.ToString());
            }
        }

        
        public  void ReceiveCallback(IAsyncResult ar)
        {
            try
            {
                Socket socket = (Socket)ar.AsyncState;
                int count = socket.EndReceive(ar);
                if (count == 0)
                    return;
       
                string readstr = System.Text.Encoding.Default.GetString(readBuff, 0, count);
                SetProgressDelegate setprogress = new SetProgressDelegate(SetProgress);
                this.Invoke(setprogress, new object[] { "Client:" + readstr,"" });

                socket.BeginReceive(readBuff, 0, 1024, 0, ReceiveCallback, socket);

            }
            catch (Exception e)
            {
                Console.WriteLine("Socket Reveive fail: " + e.ToString());
            }
        }

        public void SendCallback(IAsyncResult ar)
        {
            try
            {
                Socket socket = (Socket)ar.AsyncState;
                socket.EndSend(ar); 

            }catch(Exception e)
            {
                Console.WriteLine(e.ToString());
            }
        }

    }
}
