VERSION 5.00
Begin VB.Form Form2 
   BackColor       =   &H8000000C&
   Caption         =   "Японский кростворд Результат"
   ClientHeight    =   11010
   ClientLeft      =   2820
   ClientTop       =   450
   ClientWidth     =   11880
   LinkTopic       =   "Form2"
   ScaleHeight     =   8000
   ScaleMode       =   0  'Пользовательское
   ScaleWidth      =   8000
   Begin VB.CommandButton Command2 
      Caption         =   "вихід"
      Height          =   495
      Left            =   6720
      TabIndex        =   1
      Top             =   10200
      Width           =   3735
   End
   Begin VB.CommandButton Command1 
      Caption         =   "<<<< назад"
      Height          =   495
      Left            =   720
      TabIndex        =   0
      Top             =   10200
      Width           =   3975
   End
End
Attribute VB_Name = "Form2"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False

Private Sub Command1_Click()
Form2.Visible = False
Form1.Visible = True

End Sub


Private Sub Command2_Click()
End
End Sub

Private Sub Form_Activate()
graph
End Sub

Private Sub Form_Paint()
graph
End Sub

Private Sub Form_Unload(Cancel As Integer)
End
End Sub
Private Sub graph()
For i = 0 To gor
Line (0 + 1000, i * 100 + 1000)-(ver * 100 + 1000, i * 100 + 1000)
Next i
For i = 0 To ver
Line (i * 100 + 1000, 0 + 1000)-(i * 100 + 1000, gor * 100 + 1000)
Next i
For j = 0 To 59
For i = 0 To 59
    If t(j, i) <> 1 Then GoTo 6
    For u = 0 To 99 Step 10
        Line (i * 100 + 1000, j * 100 + 1000 + u)-(i * 100 + 1100, j * 100 + 1000 + u)
    Next u
6 Next i
Next j

For j = 0 To 59
For i = 0 To 59
    If t(j, i) <> 5 Then GoTo 16
    For u = 0 To 99 Step 10
        Line (i * 100 + 1000, j * 100 + 1000 + u)-(i * 100 + 1100, j * 100 + 1000 + u), &H80000009
    Next u
16 Next i
Next j

End Sub
