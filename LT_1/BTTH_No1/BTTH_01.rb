# class BTTH1
#   def initialize(id)
#     @id = id
#   end
#   def run
#     print "T-#{@id} "
#   end
# end 
# t1 = Thread.new do
#   BTTH1.new(15).run
# end

# t2 = Thread.new do
#   BTTH1.new(3).run
# end
# t1.join
# t2.join
# print "T-m "

# //////////////////////////////////////
  # @a.each do |value|
    #   if value > @max_int
    #     @max_int = value
    #   end
    # end
    # puts "Số lớn nhất = #{@max_int}"
require 'pry'

class BTTH
  def initialize(n, k)
    @k = k
    @a = Array.new(n) { rand(10) }
    @max_int = @a[0]
    @part_size = (@a.length / @k.to_f).ceil
    @groups = @a.each_slice(@part_size).to_a
  end
  def multithreading
    # Duyệt qua mảng để tìm số lớn nhất
    results = Array.new(@k)
    results_total = Array.new(@k)  # Mảng chứa kết quả max của từng nhóm
    threads = []
    @k.times do |i|
      threads << Thread.new do
        part = @groups[i]       
        part_max = part.max
        results_total[i] = part.sum
        results[i] = part_max
      end
    end
    threads.each do |t|
      t.join
    end
    # @max_int = results.max
    puts "Số lớn nhất = #{results.max}"
    puts " Tổng các phần tử= #{results_total.sum}"

  end

end

btth = BTTH.new(N = 10,K = 3)
# btth.da_luong

btth.multithreading
# btth.tinh_tong
