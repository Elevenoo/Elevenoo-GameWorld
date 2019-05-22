namespace EchoServer
{
    partial class ServerWin
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.Listen_btn = new System.Windows.Forms.Button();
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.Dialog = new System.Windows.Forms.ListBox();
            this.Online = new System.Windows.Forms.ListBox();
            this.Send_btn = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // Listen_btn
            // 
            this.Listen_btn.Location = new System.Drawing.Point(22, 12);
            this.Listen_btn.Name = "Listen_btn";
            this.Listen_btn.Size = new System.Drawing.Size(107, 23);
            this.Listen_btn.TabIndex = 0;
            this.Listen_btn.Text = "Listen";
            this.Listen_btn.UseVisualStyleBackColor = true;
            this.Listen_btn.Click += new System.EventHandler(this.Listen_btn_Click);
            // 
            // textBox1
            // 
            this.textBox1.Location = new System.Drawing.Point(169, 403);
            this.textBox1.Name = "textBox1";
            this.textBox1.Size = new System.Drawing.Size(213, 21);
            this.textBox1.TabIndex = 1;
            // 
            // Dialog
            // 
            this.Dialog.FormattingEnabled = true;
            this.Dialog.ItemHeight = 12;
            this.Dialog.Location = new System.Drawing.Point(169, 12);
            this.Dialog.Name = "Dialog";
            this.Dialog.Size = new System.Drawing.Size(287, 376);
            this.Dialog.TabIndex = 2;
            // 
            // Online
            // 
            this.Online.BackColor = System.Drawing.SystemColors.InactiveBorder;
            this.Online.Enabled = false;
            this.Online.FormattingEnabled = true;
            this.Online.ItemHeight = 12;
            this.Online.Location = new System.Drawing.Point(22, 60);
            this.Online.Name = "Online";
            this.Online.Size = new System.Drawing.Size(107, 364);
            this.Online.TabIndex = 3;
            // 
            // Send_btn
            // 
            this.Send_btn.Location = new System.Drawing.Point(388, 401);
            this.Send_btn.Name = "Send_btn";
            this.Send_btn.Size = new System.Drawing.Size(68, 23);
            this.Send_btn.TabIndex = 4;
            this.Send_btn.Text = "Send";
            this.Send_btn.UseVisualStyleBackColor = true;
            this.Send_btn.Click += new System.EventHandler(this.Send_btn_Click);
            // 
            // ServerWin
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(491, 450);
            this.Controls.Add(this.Send_btn);
            this.Controls.Add(this.Online);
            this.Controls.Add(this.Dialog);
            this.Controls.Add(this.textBox1);
            this.Controls.Add(this.Listen_btn);
            this.Name = "ServerWin";
            this.Text = "Server";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button Listen_btn;
        private System.Windows.Forms.TextBox textBox1;
        private System.Windows.Forms.ListBox Dialog;
        private System.Windows.Forms.ListBox Online;
        private System.Windows.Forms.Button Send_btn;
    }
}